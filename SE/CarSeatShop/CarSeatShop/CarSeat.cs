public class CarSeat
{
    public int Id { get; set; } 
    public string Model { get; set; }
    public string Brand { get; set; }
    public double Price { get; set; }
    public int AgeFrom { get; set; }

    public CarSeat(string model, string brand, double price, int ageFrom)
    {
        Model = model;
        Brand = brand;
        Price = price;
        AgeFrom = ageFrom;
    }

    public CarSeat() { }
}

