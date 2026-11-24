public class BillingService {
    // Common Coupling усунено: глобальної змінної GLOBAL_TAX_RATE більше немає.
    // Значення за замовчуванням – незмінна константа, яку неможливо перезаписати.
    private static final double DEFAULT_TAX_RATE = 0.20;

    public static class CustomerProfile {
        public int userId;
        public String email;
        public String passwordHash;
        public String city;
        public int loyaltyPoints;

        public CustomerProfile(int userId, String email, String passwordHash, String city, int loyaltyPoints) {
            this.userId = userId;
            this.email = email;
            this.passwordHash = passwordHash;
            this.city = city;
            this.loyaltyPoints = loyaltyPoints;
        }
    }

    // Stamp Coupling усунено: лише примітивні параметри (city, weightKg)
    public static double calculateDeliveryCost(String city, double weightKg) {
        double baseRate = "Ivano-Frankivsk".equals(city) ? 50.0 : 110.0;
        return baseRate + (weightKg * 12.0);
    }

    // Control Coupling усунено: окремий метод замість modeFlag == 1.
    // Ставка податку передається явно.
    public static String processStandardInvoice(double amount, String city, double weightKg, double taxRate) {
        double delivery = calculateDeliveryCost(city, weightKg);
        double total = (amount * (1 + taxRate)) + delivery;
        return "PROCESSED: " + Math.round(total * 100.0) / 100.0;
    }

    // Перевантажений метод без taxRate: викликає основний зі значенням 0.20
    public static String processStandardInvoice(double amount, String city, double weightKg) {
        return processStandardInvoice(amount, city, weightKg, DEFAULT_TAX_RATE);
    }

    // Control Coupling усунено: окремий метод замість modeFlag == 2
    public static String processTaxExemptInvoice(double amount, String city, double weightKg) {
        double total = amount + calculateDeliveryCost(city, weightKg);
        return "TAX_EXEMPT: " + Math.round(total * 100.0) / 100.0;
    }
}
