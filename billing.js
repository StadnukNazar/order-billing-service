function calculateDeliveryCost(city, weightKg) {
const baseRate = city === "Kyiv" ? 80.0 : city === "Ivano-Frankivsk" ? 50.0 : 110.0;
    return baseRate + (weightKg * 12.0);
}

function processStandardInvoice(amount, city, weightKg, taxRate = 0.20) {
    const delivery = calculateDeliveryCost(city, weightKg);
    const total = (amount * (1 + taxRate)) + delivery;
    return { status: "PROCESSED", total: Number(total.toFixed(2)) };
}

function processTaxExemptInvoice(amount, city, weightKg) {
    const total = amount + calculateDeliveryCost(city, weightKg);
    return { status: "TAX_EXEMPT", total: Number(total.toFixed(2)) };
}

module.exports = {
    calculateDeliveryCost,
    processStandardInvoice,
    processTaxExemptInvoice
};
