package decorator;
/* Streams of.... contents of files
May have different types of Streams --> BufferedStream --> Zip/Compression

Replacing inheritance with a container.
Still have Stream + basic implementation

Different decorators: one is for bufferisation, one is for compression, etc.
Can combine them in any way you like

Dynamic subclasses by using different sequences.
*/
public class DecoratorGoF {

    public static void main(String... args) {
        SalaryCalculator calculator =
                new HealthInsuranceDecorator(
                        new RegionalTaxDecorator(
                                new GeneralTaxDecorator(
                                        new DefaultSalaryCalculator())));

        System.out.println(calculator.calculate(30000.00));
    }

    interface SalaryCalculator {
        double calculate(double grossAnnual);
    }

    public static class DefaultSalaryCalculator implements SalaryCalculator {

        @Override
        public double calculate(double grossAnnual) {
            return grossAnnual / 12;
        }
    }

    // allows us to chain the different subclasses together
    public static abstract class AbstractTaxDecorator implements SalaryCalculator {
        private final SalaryCalculator salaryCalculator;

        public AbstractTaxDecorator(SalaryCalculator salaryCalculator) {
            this.salaryCalculator = salaryCalculator;
        }

        protected abstract double applyTax(double salary);

        @Override
        public final double calculate(double grossAnnual) {
            double salary = salaryCalculator.calculate(grossAnnual);
            return applyTax(salary);
        }
    }

    public static class GeneralTaxDecorator extends AbstractTaxDecorator {
        public GeneralTaxDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return Taxes.generalTax(salary);
        }
    }

    public static class RegionalTaxDecorator extends AbstractTaxDecorator {
        public RegionalTaxDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return Taxes.regionalTax(salary);
        }
    }

    public static class HealthInsuranceDecorator extends AbstractTaxDecorator {
        public HealthInsuranceDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return Taxes.healthInsurance(salary);
        }
    }
}
