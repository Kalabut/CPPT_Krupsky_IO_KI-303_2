using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using Newtonsoft.Json;

namespace CarSeatServer
{
    public class CarSeat
    {
        public int Id { get; set; }
        public string Model { get; set; }
        public string Brand { get; set; }
        public double Price { get; set; }
        public int AgeFrom { get; set; }
    }

    class Program
    {
        private static string DbPath = "db.json";

        static void Main()
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            Console.InputEncoding = System.Text.Encoding.UTF8;

            Console.Title = "TCP-сервер автокрісел";

            CreateDatabaseIfNotExists();

            TcpListener listener = new TcpListener(IPAddress.Any, 9000);
            listener.Start();

            Console.WriteLine("Сервер запущено на порту 9000");
            Console.WriteLine("Очікування клієнтів...\n");

            while (true)
            {
                TcpClient client = listener.AcceptTcpClient();
                HandleClient(client);
            }
        }

        private static void HandleClient(TcpClient client)
        {
            try
            {
                using (client)
                using (NetworkStream stream = client.GetStream())
                using (StreamReader reader = new StreamReader(stream))
                using (StreamWriter writer = new StreamWriter(stream))
                {
                    writer.AutoFlush = true;

                    string command = reader.ReadLine();
                    Console.WriteLine("Отримано: " + command);

                    string response = Process(command);

                    writer.WriteLine(response);
                    Console.WriteLine("Відповідь відправлена\n");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Помилка: " + ex.Message);
            }
        }

        private static string Process(string cmd)
        {
            if (cmd == null) return "ERROR";

            try
            {
                if (cmd == "GET_ALL")
                {
                    return JsonConvert.SerializeObject(LoadItems());
                }

                if (cmd.StartsWith("SEARCH:"))
                {
                    string q = cmd.Substring(7).ToLower();
                    var list = LoadItems();

                    var result = list.FindAll(s =>
                        s.Brand.ToLower().Contains(q) ||
                        s.Model.ToLower().Contains(q) ||
                        s.Price.ToString().Contains(q)
                    );

                    return JsonConvert.SerializeObject(result);
                }

                if (cmd.StartsWith("ADD:"))
                {
                    string json = cmd.Substring(4);
                    CarSeat item = JsonConvert.DeserializeObject<CarSeat>(json);

                    var list = LoadItems();

                    item.Id = list.Count == 0 ? 1 : list[list.Count - 1].Id + 1;

                    list.Add(item);
                    SaveItems(list);

                    return "OK";
                }


                if (cmd.StartsWith("DELETE:"))
                {
                    string json = cmd.Substring(7);
                    CarSeat item = JsonConvert.DeserializeObject<CarSeat>(json);

                    var list = LoadItems();
                    list.RemoveAll(x => x.Id == item.Id);
                    SaveItems(list);

                    return "OK";
                }

                return "ERROR:Unknown command";
            }
            catch (Exception ex)
            {
                return "ERROR:" + ex.Message;
            }
        }

        private static void CreateDatabaseIfNotExists()
        {
            if (!File.Exists(DbPath))
            {
                List<CarSeat> defaultList = new List<CarSeat>
        {
            new CarSeat { Id = 1,  Model="BabyComfort",        Brand="Recaro",        Price=5600, AgeFrom=1 },
            new CarSeat { Id = 2,  Model="SafeRide",           Brand="Britax",        Price=7200, AgeFrom=2 },
            new CarSeat { Id = 3,  Model="ComfortLine",        Brand="Chicco",        Price=4900, AgeFrom=3 },
            new CarSeat { Id = 4,  Model="Kiddy Evo-Luna",     Brand="Kiddy",         Price=8500, AgeFrom=0 },
            new CarSeat { Id = 5,  Model="Romer King II",      Brand="Britax Römer",  Price=9100, AgeFrom=1 },
            new CarSeat { Id = 6,  Model="BeSafe iZi Twist",   Brand="BeSafe",        Price=11800, AgeFrom=0 },
            new CarSeat { Id = 7,  Model="PegPerego Viaggio",  Brand="Peg Perego",    Price=7650, AgeFrom=2 },
            new CarSeat { Id = 8,  Model="Maxi-Cosi Titan",    Brand="Maxi-Cosi",     Price=9300, AgeFrom=1 },
            new CarSeat { Id = 9,  Model="Joie i-Spin 360",    Brand="Joie",          Price=8800, AgeFrom=0 },
            new CarSeat { Id = 10, Model="Cybex Solution B2",  Brand="Cybex",         Price=6200, AgeFrom=3 },
            new CarSeat { Id = 11, Model="Evenflo EveryStage", Brand="Evenflo",       Price=7100, AgeFrom=1 },
            new CarSeat { Id = 12, Model="Graco SlimFit",      Brand="Graco",         Price=6700, AgeFrom=2 },
            new CarSeat { Id = 13, Model="Lionelo Sander",     Brand="Lionelo",       Price=5400, AgeFrom=1 },
            new CarSeat { Id = 14, Model="Nania Cosmo SP",     Brand="Nania",         Price=3700, AgeFrom=0 },
            new CarSeat { Id = 15, Model="Zlatek Atlantic",    Brand="Zlatek",        Price=3200, AgeFrom=0 }
        };

                File.WriteAllText(DbPath,
                    JsonConvert.SerializeObject(defaultList, Formatting.Indented));
            }
        }


        private static List<CarSeat> LoadItems()
        {
            string json = File.ReadAllText(DbPath);
            return JsonConvert.DeserializeObject<List<CarSeat>>(json);
        }

        private static void SaveItems(List<CarSeat> items)
        {
            File.WriteAllText(DbPath,
                JsonConvert.SerializeObject(items, Formatting.Indented));
        }
    }
}
