package lib;

public class TaxFunction {

    public static int calculateTax(
        int monthlySalary,
        int otherMonthlyIncome,
        int numberOfMonthWorking,
        int deductible,
        boolean isMarried,
        int numberOfChildren
    ) {
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 months working per year");
            numberOfMonthWorking = 12;
        }

        numberOfChildren = Math.min(numberOfChildren, 3);

        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int taxableIncome = annualIncome - deductible - nonTaxableIncome;

        return Math.max(0, (int) Math.round(taxableIncome * 0.05));
    }

    private static int calculateAnnualIncome(int salary, int otherIncome, int monthsWorked) {
        return (salary + otherIncome) * monthsWorked;
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int basePTKP = 54000000;
        int marriedPTKP = isMarried ? 4500000 : 0;
        int childrenPTKP = numberOfChildren * 1500000;
        return basePTKP + marriedPTKP + childrenPTKP;
    }
}
