# Release v1.1.0 – Data Coupling & Updated Tariffs

Зміни у версії v1.1.0 порівняно з v1.0.0.

## Added
- Спеціальний тариф для міста Київ: базова ставка доставки 80.0 (для Івано-Франківська лишається 50.0, для інших міст – 110.0).
- Явний параметр ставки податку `taxRate` у методі `processStandardInvoice(amount, city, weightKg, taxRate)`.
- Перевантажений метод `processStandardInvoice(amount, city, weightKg)`, який викликає основний метод зі ставкою за замовчуванням 0.20.

## Changed
- Коефіцієнт вартості доставки за вагу збільшено з 12.0 до 15.0 за кілограм.
- Виконано перехід до Data Coupling: методи приймають лише примітивні параметри (`city`, `weightKg`), глобальний стан і прапорці режимів усунено.
- Логіку `processInvoice` розділено на два окремі методи: `processStandardInvoice(...)` та `processTaxExemptInvoice(...)`.

## Breaking Changes
- Змінено сигнатуру `calculateDeliveryCost`: замість `(CustomerProfile customer, double weightKg)` тепер `(String city, double weightKg)`.
- Видалено метод `processInvoice(amount, customer, weightKg, modeFlag)`; замість нього використовуйте `processStandardInvoice(...)` або `processTaxExemptInvoice(...)`.
- Видалено глобальну змінну `GLOBAL_TAX_RATE`; ставка податку передається аргументом `taxRate`.
- Видалено прапорець `modeFlag`; режим «CANCELLED» (невідомий `modeFlag`) більше не повертається.
- Вартість доставки зросла внаслідок нового коефіцієнта 15.0 (наприклад, для Києва з вагою 2 кг: 110.0 замість 104.0).
