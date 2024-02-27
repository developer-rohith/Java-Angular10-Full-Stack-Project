import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']

})
export class UpdateEmployeeComponent implements OnInit {
  employee: Employee = new Employee();
  id!: number;

  constructor(private employeeService: EmployeeService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeeService.getEmployee(this.id).subscribe(data => {
      this.employee = data;
    });
  }
  updEmployee() {
    this.employeeService.updateEmployee(this.id, this.employee).subscribe(data => {

      this.goToEmployeesList();

    },
      error => console.log(error));
  }



  goToEmployeesList() {
    this.router.navigate(['/employees']);

  }

  onSubmit() {
    this.updEmployee();



  }

}
