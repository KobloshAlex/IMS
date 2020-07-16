import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Manager} from '../manager';
import {ManagerService} from '../../../service/manager.service';

@Component({
  selector: 'app-show-manager',
  templateUrl: './show-manager.component.html',
  styleUrls: ['./show-manager.component.css']
})
export class ShowManagerComponent implements OnInit {

  manager: Observable<Manager[]>;

  constructor(private managerService: ManagerService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchManagerList();
  }

  fetchManagerList() {
    this.manager = this.managerService.getAllManagers();
  }

  deleteManager(id: string) {
    this.managerService.deleteManager(id).subscribe(data => {
        console.log(data);
        this.fetchManagerList();
      },
      error => console.log(error));
  }

  managerDetails(id: string) {
    this.router.navigate(['managerDetails', id]);
  }

  managerUpdate(manager: Manager) {
    this.router.navigate(['updateManager', manager]);
  }
}
