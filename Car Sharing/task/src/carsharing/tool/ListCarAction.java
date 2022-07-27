package carsharing.tool;

import carsharing.Car;
import carsharing.Company;
import carsharing.CompanyService;

import java.util.List;

public class ListCarAction implements Action {
    private final Company company;
    private final CompanyService companyService;

    public ListCarAction(Company company, CompanyService companyService) {
        this.company = company;
        this.companyService = companyService;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        final List<Car> cars = companyService.getAllCars(company);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            for (int i = 1; i <= cars.size(); i++) {
                Car car = cars.get(i - 1);
                System.out.printf("%d. %s%n", i, car.getName());
            }
            System.out.println();
        }
    }
}
