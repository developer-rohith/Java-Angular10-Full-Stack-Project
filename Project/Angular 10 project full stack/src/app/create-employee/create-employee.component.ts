import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();


  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit(): void {

  }

  saveEmployee() {
    return this.employeeService.createEmployee(this.employee).subscribe(data => {
      console.log(data);
    },
      error => console.log(error));
  }

  goToEmployeesList() {
    this.router.navigate(['/employees']);

  }
  onSubmit() {

    this.saveEmployee();
    this.goToEmployeesList();



  }

}
