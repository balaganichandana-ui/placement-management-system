import java.util.*;

public class Main {

    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Company> companies = new ArrayList<>();
    static ArrayList<placement> placements = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Placement Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Company");
            System.out.println("4. View Companies");
            System.out.println("5. Place Student");
            System.out.println("6. View Placements");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    addCompany();
                    break;
                case 4:
                    viewCompanies();
                    break;
                case 5:
                    placeStudent();
                    break;
                case 6:
                    viewPlacements();
                    break;
                case 7:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ---------- Student Methods ----------
    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Branch: ");
        String branch = sc.nextLine();

        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();

        students.add(new Student(id, name, branch, cgpa));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(
                "ID: " + s.id +
                ", Name: " + s.name +
                ", Branch: " + s.branch +
                ", CGPA: " + s.cgpa
            );
        }
    }

    // ---------- Company Methods ----------
    static void addCompany() {
        System.out.print("Enter Company ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Company Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Job Role: ");
        String role = sc.nextLine();

        System.out.print("Enter Minimum CGPA: ");
        double minCgpa = sc.nextDouble();

        companies.add(new Company(id, name, role, minCgpa));
        System.out.println("Company added successfully!");
    }

    static void viewCompanies() {
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }

        System.out.println("\n--- Company List ---");
        for (Company c : companies) {
            System.out.println(
                "ID: " + c.id +
                ", Name: " + c.name +
                ", Role: " + c.role +
                ", Min CGPA: " + c.minCGPA
            );
        }
    }

    // ---------- Placement Methods ----------
    static void placeStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        System.out.print("Enter Company ID: ");
        int companyId = sc.nextInt();

        Student selectedStudent = null;
        Company selectedCompany = null;

        for (Student s : students) {
            if (s.id == studentId) {
                selectedStudent = s;
                break;
            }
        }

        for (Company c : companies) {
            if (c.id == companyId) {
                selectedCompany = c;
                break;
            }
        }

        if (selectedStudent == null || selectedCompany == null) {
            System.out.println("Invalid student or company ID.");
            return;
        }

        if (selectedStudent.cgpa < selectedCompany.minCGPA) {
            System.out.println("Student not eligible.");
            return;
        }

        placements.add(new placement(studentId, companyId));
        System.out.println("Student placed successfully!");
    }

    static void viewPlacements() {
        if (placements.isEmpty()) {
            System.out.println("No placements yet.");
            return;
        }

        System.out.println("\n--- Placement Records ---");
        for (placement p : placements) {
            String studentName = "";
            String companyName = "";

            for (Student s : students) {
                if (s.id == p.studentid) {
                    studentName = s.name;
                    break;
                }
            }

            for (Company c : companies) {
                if (c.id == p.companyid) {
                    companyName = c.name;
                    break;
                }
            }

            System.out.println("Student: " + studentName + " → Company: " + companyName);
        }
    }
}