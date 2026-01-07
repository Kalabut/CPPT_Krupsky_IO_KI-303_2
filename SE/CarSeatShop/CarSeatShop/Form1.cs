using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Forms;
using Newtonsoft.Json;

namespace CarSeatShop
{
    public partial class Form1 : Form
    {
        public BindingList<CarSeat> Seats { get; set; } = new BindingList<CarSeat>();

        public Form1()
        {
            InitializeComponent();
            StyleManager.ApplyStyles(this);
            this.Text = "Інтернет-магазин автокрісел";

            LoadSeats();
        }

        // ------------------------ ЗАВАНТАЖЕННЯ ВСІХ ТОВАРІВ ------------------------
        private void LoadSeats()
        {
            var list = TcpClientHelper.SendCommand("GET_ALL");

            if (list == null)
            {
                MessageBox.Show("Сервер не відповідає!", "Помилка TCP");
                return;
            }

            Seats = new BindingList<CarSeat>(list);
            dataGridView1.DataSource = Seats;

            if (dataGridView1.Columns.Count > 0)
            {
                dataGridView1.Columns["Model"].HeaderText = "Модель";
                dataGridView1.Columns["Brand"].HeaderText = "Бренд";
                dataGridView1.Columns["Price"].HeaderText = "Ціна (грн)";
                dataGridView1.Columns["AgeFrom"].HeaderText = "Вік з";
            }
        }

        // ------------------------ ПОШУК ------------------------
        private void btnSearch_Click(object sender, EventArgs e)
        {
            string query = textBoxSearch.Text.Trim();

            if (query == "")
            {
                LoadSeats();
                return;
            }

            var list = TcpClientHelper.SendCommand("SEARCH:" + query);

            if (list == null || list.Count == 0)
            {
                MessageBox.Show("Нічого не знайдено!");
                return;
            }

            Seats = new BindingList<CarSeat>(list);
            dataGridView1.DataSource = Seats;
        }

        // ------------------------ ДОДАТИ ------------------------
        private void btnAdd_Click(object sender, EventArgs e)
        {
            string brand = Prompt("Введіть бренд:");
            string model = Prompt("Введіть модель:");
            string priceStr = Prompt("Введіть ціну:");
            string ageStr = Prompt("Вік дитини з:");

            if (brand == "" || model == "")
            {
                MessageBox.Show("Бренд і модель потрібні!");
                return;
            }

            if (!double.TryParse(priceStr, out double price) ||
                !int.TryParse(ageStr, out int age))
            {
                MessageBox.Show("Невірний формат числа!");
                return;
            }

            var item = new CarSeat
            {
                Brand = brand,
                Model = model,
                Price = price,
                AgeFrom = age
            };

            string json = JsonConvert.SerializeObject(item);

            TcpClientHelper.SendRaw("ADD:" + json);

            MessageBox.Show("Товар додано!");

            LoadSeats();
        }

        // ------------------------ ВИДАЛИТИ ------------------------
        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentRow == null)
            {
                MessageBox.Show("Виберіть товар!");
                return;
            }

            var item = dataGridView1.CurrentRow.DataBoundItem as CarSeat;

            string json = JsonConvert.SerializeObject(item);
            TcpClientHelper.SendRaw("DELETE:" + json);

            MessageBox.Show("Товар видалено!");

            LoadSeats();
        }

        // ------------------------ ВИХІД ------------------------
        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        // ------------------------ PROMPT ВВЕДЕННЯ ------------------------
        private string Prompt(string text)
        {
            Form f = new Form();
            f.Width = 350;
            f.Height = 150;
            f.Text = text;
            f.StartPosition = FormStartPosition.CenterParent;

            Label lbl = new Label() { Left = 10, Top = 10, Text = text, Width = 300 };
            TextBox input = new TextBox() { Left = 10, Top = 40, Width = 300 };
            Button ok = new Button() { Text = "OK", Left = 220, Width = 80, Top = 70 };

            ok.DialogResult = DialogResult.OK;

            f.Controls.Add(lbl);
            f.Controls.Add(input);
            f.Controls.Add(ok);
            f.AcceptButton = ok;

            return f.ShowDialog() == DialogResult.OK ? input.Text : "";
        }
    }
}
