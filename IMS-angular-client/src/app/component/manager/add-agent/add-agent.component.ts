import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';

import {Router} from '@angular/router';
import {Manager} from '../manager';
import {Agent} from '../../agent/agent';
import {AgentService} from '../../../service/agent.service';
import {ManagerService} from '../../../service/manager.service';

@Component({
  selector: 'app-add-agent',
  templateUrl: './add-agent.component.html',
  styleUrls: ['./add-agent.component.css']
})
export class AddAgentComponent implements OnInit {

  managerList: Observable<Manager[]>;
  agentList: Observable<Agent[]>;
  manager: Manager;
  managerId: string;
  agentId: string;
  public error: any;

  constructor(private agentService: AgentService, private managerService: ManagerService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchManagerList();
    this.fetchAgentList();
  }

  fetchManagerList() {
    this.managerList = this.managerService.getAllManagers();
  }

  fetchAgentList() {
    this.agentList = this.agentService.getAllAgents();
  }

  addAgentToManager() {
    this.managerService.addAgentToManager(this.manager, this.managerId, this.agentId).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.error = error.error.message;
    });
    this.manager = new Manager();
    this.goBack();
  }

  submitAgent() {
    this.addAgentToManager();
  }

  goBack() {
    this.router.navigate(['managers']);
  }
}
