using System.Drawing;
using System.Windows.Forms;

namespace CarSeatShop
{
    public static class StyleManager
    {
        // Основні кольори
        public static Color BackgroundColor = Color.LightSteelBlue;
        public static Color ButtonColor = Color.LightSkyBlue;
        public static Color ButtonTextColor = Color.Black;
        public static Color LabelTextColor = Color.Navy;
        public static Font DefaultFont = new Font("Segoe UI", 10);
        public static Font TitleFont = new Font("Segoe UI", 12, FontStyle.Bold);

        // Метод застосування стилів до елементів
        public static void ApplyStyles(Form form)
        {
            form.BackColor = BackgroundColor;
            form.Font = DefaultFont;

            foreach (Control control in form.Controls)
            {
                if (control is Button btn)
                {
                    btn.BackColor = ButtonColor;
                    btn.ForeColor = ButtonTextColor;
                    btn.FlatStyle = FlatStyle.Flat;
                    btn.Font = DefaultFont;
                }
                else if (control is Label lbl)
                {
                    lbl.ForeColor = LabelTextColor;
                    lbl.Font = TitleFont;
                }
                else if (control is TextBox txt)
                {
                    txt.Font = DefaultFont;
                }
                else if (control is DataGridView dgv)
                {
                    dgv.BackgroundColor = Color.White;
                    dgv.Font = DefaultFont;
                }
            }
        }
    }
}
