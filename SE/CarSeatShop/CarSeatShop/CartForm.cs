using System;
using System.ComponentModel;
using System.Linq;
using System.Windows.Forms;

namespace CarSeatShop
{
    public partial class CartForm : Form
    {
        private BindingList<CarSeat> Cart;

        public CartForm(BindingList<CarSeat> cart)
        {
            InitializeComponent();
            StyleManager.ApplyStyles(this);
            this.Text = "Кошик";

            Cart = cart;

            dataGridViewCart.DataSource = Cart;
            dataGridViewCart.ReadOnly = true;
            dataGridViewCart.AllowUserToAddRows = false;
            dataGridViewCart.AllowUserToDeleteRows = false;

            dataGridViewCart.Columns["Model"].HeaderText = "Модель";
            dataGridViewCart.Columns["Brand"].HeaderText = "Бренд";
            dataGridViewCart.Columns["Price"].HeaderText = "Ціна (грн)";
            dataGridViewCart.Columns["AgeFrom"].HeaderText = "Вік з (років)";

            UpdateTitle();
        }

        private void UpdateTitle()
        {
            double total = Cart.Sum(c => c.Price);
            lblTitle.Text = $"Ваш кошик — {Cart.Count} товар(ів), на суму {total} грн";
        }

        private void btnRemove_Click(object sender, EventArgs e)
        {
            if (dataGridViewCart.CurrentRow != null)
            {
                var selected = dataGridViewCart.CurrentRow.DataBoundItem as CarSeat;
                if (selected != null)
                {
                    Cart.Remove(selected);
                    UpdateTitle();
                }
            }
            else
            {
                MessageBox.Show("Оберіть товар для видалення!");
            }
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            if (Cart.Count == 0)
            {
                MessageBox.Show("Кошик уже порожній.");
                return;
            }

            var confirm = MessageBox.Show("Очистити весь кошик?", "Підтвердження", MessageBoxButtons.YesNo);
            if (confirm == DialogResult.Yes)
            {
                Cart.Clear();
                UpdateTitle();
            }
        }

        private void btnOrder_Click(object sender, EventArgs e)
        {
            if (Cart.Count == 0)
            {
                MessageBox.Show("Неможливо оформити — кошик порожній.");
                return;
            }

            double total = Cart.Sum(c => c.Price);
            MessageBox.Show($"Дякуємо за замовлення!\n\nСума: {total} грн.\nНаш менеджер зв'яжеться з вами найближчим часом 😊",
                "Замовлення оформлено");

            Cart.Clear();
            UpdateTitle();
        }

        private void btnClose_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
