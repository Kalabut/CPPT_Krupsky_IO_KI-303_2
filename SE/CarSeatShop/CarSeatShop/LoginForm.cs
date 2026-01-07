using System;
using System.ComponentModel;
using System.Windows.Forms;

namespace CarSeatShop
{
    public partial class LoginForm : Form
    {
        public LoginForm()
        {
            InitializeComponent();
            this.Text = "Авторизація";
            StyleManager.ApplyStyles(this);
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            string login = textBoxLogin.Text.Trim();
            string password = textBoxPassword.Text.Trim();

            try
            {
                if (login == "admin" && password == "1234")
                {
                    MessageBox.Show("Вітаємо, адміністраторе!");
                    this.Hide();
                    Form1 adminForm = new Form1();
                    adminForm.ShowDialog();
                    this.Show();
                }
                else if (login == "user" && password == "0000")
                {
                    MessageBox.Show("Вітаємо, користувачу!");
                    this.Hide();
                    CustomerForm customerForm = new CustomerForm();
                    customerForm.ShowDialog();
                    this.Show();
                }
                else
                {
                    MessageBox.Show("Невірний логін або пароль!", "Помилка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Помилка авторизації: " + ex.Message);
            }
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void btnLogin_Click_1(object sender, EventArgs e)
        {
            string login = textBoxLogin.Text.Trim();
            string password = textBoxPassword.Text.Trim();

            try
            {

                if (login == "admin" && password == "1234")
                {
                    MessageBox.Show("Вітаємо, адміністраторе!");
                    this.Hide();
                    Form1 adminForm = new Form1();
                    adminForm.ShowDialog();
                    this.Show();
                }
                else if (login == "user" && password == "0000")
                {
                    MessageBox.Show("Вітаємо, користувачу!");
                    this.Hide();
                    CustomerForm customerForm = new CustomerForm();
                    customerForm.ShowDialog();
                    this.Show();
                }
                else
                {
                    MessageBox.Show("Невірний логін або пароль!", "Помилка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Помилка авторизації: " + ex.Message);
            }
        }


    }
}
