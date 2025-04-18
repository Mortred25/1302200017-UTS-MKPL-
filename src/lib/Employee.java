package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private PersonalData personalData;
    private JoiningDate joiningDate;

    private int monthWorkingInYear;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(PersonalData personalData, JoiningDate joiningDate) {
        this.personalData = personalData;
        this.joiningDate = joiningDate;
        this.childNames = new LinkedList<>();
        this.childIdNumbers = new LinkedList<>();
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya SSSSS
     * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
     * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
     */
    public void setMonthlySalary(int grade) {
        switch (grade) {
            case 1:
                monthlySalary = 3000000;
                break;S
            case 2:
                monthlySalary = 5000000;
                break;
            case 3:
                monthlySalary = 7000000;
                break;
            default:
                monthlySalary = 0;
                break;
        }

        if (personalData.isForeigner()) {
            monthlySalary *= 1.5;
        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        LocalDate date = LocalDate.now();

        if (date.getYear() == joiningDate.getYear()) {
            monthWorkingInYear = date.getMonthValue() - joiningDate.getMonth();
        } else {
            monthWorkingInYear = 12;
        }

        boolean hasSpouse = (spouseIdNumber != null && !spouseIdNumber.isEmpty());

        return TaxFunction.calculateTax(
            monthlySalary,
            otherMonthlyIncome,
            monthWorkingInYear,
            annualDeductible,
            !hasSpouse,
            childIdNumbers.size()
        );
    }
}
