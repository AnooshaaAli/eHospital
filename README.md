# eHospital (A Hospital Management System)

## Overview
The **eHospital Management System** is a JavaFX-based desktop application designed to streamline and manage hospital operations. It provides functionalities for handling patient registrations, doctor scheduling, staff management, medical record storage, and more. The system uses a modular architecture with efficient database connectivity for managing hospital data securely.

---

## Features
- **Admin Module**: Manage hospital staff, payrolls, and overall system administration.
- **Doctor Module**: View and update schedules, patient records, and prescriptions.
- **Nurse Module**: Access patient details and assist in patient care.
- **Receptionist Module**: Handle patient appointments and registrations.
- **Patient Module**: Register new patients and manage patient details.
- **Database Integration**: Connects to SQL Server for secure and persistent data storage.
- **JavaFX UI**: Provides an interactive and user-friendly graphical interface.
- **Modular Design**: Each component is independent, promoting scalability and maintainability.

---

## Dependencies
The project relies on the following:
- **JavaFX SDK**: For creating GUI components.
- **JDBC Driver**: For connecting to the SQL Server database.
- **SQL Server Database**: For persistent data storage.

---

## Installation

### Prerequisites
- Java Development Kit (JDK) 17+
- JavaFX SDK
- SQL Server
- An IDE such as **Eclipse** (recommended) or **IntelliJ IDEA**.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/eHospital.git
   ```
2. Import the project into your IDE.
3. Download and configure the JavaFX SDK:
   - Add the `lib` folder of the JavaFX SDK to your project's build path.
4. Configure the SQL Server database:
   - Create a database named `eHospital`.
   - Import the required tables and sample data using the provided SQL scripts (if any).
5. Run the project:
   - Set the VM arguments in your IDE:
     ```bash
     --module-path "path-to-javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml
     ```
   - Launch the application through the `Main.java` file.

---

## Usage
1. **Login**: Access the system as an Admin, Doctor, Nurse, or Receptionist.
2. **Patient Management**: Add, update, and view patient details.
3. **Doctor Scheduling**: Manage doctor appointments and schedules.
4. **Admin Operations**: Manage hospital staff, finances, and overall system settings.
5. **Database**: All data is securely stored and managed in the SQL Server backend.

---

## Technologies Used
- **Programming Language**: Java
- **Frontend**: JavaFX (FXML)
- **Backend**: JDBC for database operations
- **Database**: SQL Server
- **IDE**: Eclipse

---

## Project Components
### Application Modules
- `Admin Module`: Manages hospital operations and staff.
- `Doctor Module`: Manages doctor-patient interactions.
- `Nurse Module`: Assists with patient care and records.
- `Receptionist Module`: Handles patient check-ins and scheduling.
- `Patient Module`: Stores and displays patient-related information.

### Libraries and Frameworks
- `JavaFX SDK`: GUI components.
- `JDBC Driver`: Database connectivity.

---

## Screenshots
_Add relevant screenshots of your application here._

---

## Acknowledgements
- **JavaFX Community**: For providing comprehensive documentation.
- **SQL Server Documentation**: For database support.
- **Eclipse IDE**: For simplifying development and debugging.

---

## Contact
For any queries or suggestions, feel free to contact:
- **Author**: [AnooshaaAli](https://github.com/AnooshaaAli), [NNoorFatima](https://github.com/NNoorFatima), [SaraAkbar16](https://github.com/SaraAkbar16)
- **Email**: i221242@nu.edu.pk, i221036@nu.edu.pk, i220846@nu.edu.pk

