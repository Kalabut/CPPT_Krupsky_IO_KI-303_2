using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using System.Windows.Forms;

namespace CarSeatShop
{
    public static class TcpClientHelper
    {
        private const string HOST = "127.0.0.1";
        private const int PORT = 9000;

        public static List<CarSeat> SendCommand(string command)
        {
            try
            {
                TcpClient client = new TcpClient();
                client.Connect(HOST, PORT);

                NetworkStream stream = client.GetStream();

                byte[] msg = Encoding.UTF8.GetBytes(command + "\n");
                stream.Write(msg, 0, msg.Length);

                // ---------------- ЧИТАННЯ ВСЬОГО ПОТОКУ ----------------
                StringBuilder jsonBuilder = new StringBuilder();
                byte[] buffer = new byte[4096];
                int bytes;

                // читаємо поки сервер не закрив з’єднання
                while ((bytes = stream.Read(buffer, 0, buffer.Length)) > 0)
                {
                    jsonBuilder.Append(Encoding.UTF8.GetString(buffer, 0, bytes));

                    // якщо JSON закінчився ]
                    if (jsonBuilder.ToString().Trim().EndsWith("]"))
                        break;
                }

                string response = jsonBuilder.ToString();

                stream.Close();
                client.Close();

                return JsonConvert.DeserializeObject<List<CarSeat>>(response);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Помилка TCP");
                return new List<CarSeat>();
            }
        }


        public static void SendRaw(string command)
        {
            try
            {
                TcpClient client = new TcpClient();
                client.Connect(HOST, PORT);

                NetworkStream stream = client.GetStream();

                byte[] msg = Encoding.UTF8.GetBytes(command + "\n");
                stream.Write(msg, 0, msg.Length);

                stream.Close();
                client.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Помилка TCP: " + ex.Message);
            }
        }
    }
}
