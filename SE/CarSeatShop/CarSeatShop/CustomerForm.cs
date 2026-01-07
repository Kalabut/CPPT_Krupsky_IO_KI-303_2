using System;
using System.ComponentModel;
using System.Linq;
using System.Windows.Forms;

namespace CarSeatShop
{
    public partial class CustomerForm : Form
    {
        private BindingList<CarSeat> Seats = new BindingList<CarSeat>();   // Список товарів із сервера
        private BindingList<CarSeat> Cart = new BindingList<CarSeat>();    // Кошик

        public CustomerForm()
        {
            InitializeComponent();
            StyleManager.ApplyStyles(this);
            this.Text = "Каталог автокрісел — режим покупця";

            LoadSeatsFromServer();
        }

        // ------------------------ ЗАВАНТАЖЕННЯ ТОВАРІВ ------------------------
        private void LoadSeatsFromServer()
        {
            var list = TcpClientHelper.SendCommand("GET_ALL");

            if (list == null)
            {
                MessageBox.Show("Помилка TCP — сервер не відповідає!");
                return;
            }

            Seats = new BindingList<CarSeat>(list);
            dataGridView1.DataSource = Seats;

            dataGridView1.ReadOnly = true;
            dataGridView1.AllowUserToAddRows = false;
            dataGridView1.AllowUserToDeleteRows = false;

            dataGridView1.Columns["Model"].HeaderText = "Модель";
            dataGridView1.Columns["Brand"].HeaderText = "Бренд";
            dataGridView1.Columns["Price"].HeaderText = "Ціна (грн)";
            dataGridView1.Columns["AgeFrom"].HeaderText = "Вік з (років)";
        }

        // ------------------------ ПОШУК ------------------------
        private void btnSearch_Click(object sender, EventArgs e)
        {
            string query = textBoxSearch.Text.Trim();

            if (string.IsNullOrEmpty(query))
            {
                LoadSeatsFromServer();
                return;
            }

            var result = TcpClientHelper.SendCommand("SEARCH:" + query);

            if (result == null || result.Count == 0)
            {
                MessageBox.Show("Нічого не знайдено!");
                return;
            }

            Seats = new BindingList<CarSeat>(result);
            dataGridView1.DataSource = Seats;
        }

        // ------------------------ ДОДАТИ В КОШИК ------------------------
        private void btnAddToCart_Click(object sender, EventArgs e)
        {
            if (dataGridView1.CurrentRow == null)
            {
                MessageBox.Show("Оберіть товар!");
                return;
            }

            var selected = dataGridView1.CurrentRow.DataBoundItem as CarSeat;

            if (selected != null)
            {
                Cart.Add(selected);
                MessageBox.Show($"Товар '{selected.Brand} {selected.Model}' додано у кошик!");
            }
        }

        // ------------------------ ВІДКРИТИ КОШИК ------------------------
        private void btnViewCart_Click(object sender, EventArgs e)
        {
            CartForm form = new CartForm(Cart);
            form.ShowDialog();
        }

        // ------------------------ ВИХІД ------------------------
        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
