import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Manager} from '../manager';
import {ManagerService} from '../../../service/manager.service';

@Component({
  selector: 'app-read-manager',
  templateUrl: './read-manager.component.html',
  styleUrls: ['./read-manager.component.css']
})
export class ReadManagerComponent implements OnInit {

  branchManagerId: string;
  manager: Manager;

  constructor(private route: ActivatedRoute, private router: Router, private managerService: ManagerService) {
  }

  ngOnInit(): void {
    this.manager = new Manager();

    this.branchManagerId = this.route.snapshot.params['branchManagerId'];


    this.managerService.getManager(this.branchManagerId).subscribe(data => {
        console.log(data);
        this.manager = data;
      },
      error => console.log(error));
  }

  backToManagers() {
    this.router.navigate(['managers']);
  }

  managerUpdate(manager: Manager) {
    this.router.navigate(['updateManager', manager]);
  }

  backToBranch() {
    this.router.navigate(['branches']);
  }
}
