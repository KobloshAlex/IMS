import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Agent} from '../agent';
import {AgentService} from '../../../service/agent.service';

@Component({
  selector: 'app-show-agents',
  templateUrl: './show-agents.component.html',
  styleUrls: ['./show-agents.component.css']
})
export class ShowAgentsComponent implements OnInit {

  agents: Observable<Agent[]>;

  constructor(private agentsService: AgentService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchAgentList();
  }

  fetchAgentList() {
    this.agents = this.agentsService.getAllAgents();
  }

  deleteAgent(id: string) {
    this.agentsService.deleteAgent(id).subscribe(data => {
        console.log(data);
        this.fetchAgentList();
      },
      error => console.log(error));
  }

  agentDetails(id: string) {
    this.router.navigate(['agentDetails', id]);
  }

  agentUpdate(agent: Agent) {
    this.router.navigate(['updateAgent', agent]);
  }
}
