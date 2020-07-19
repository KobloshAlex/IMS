import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Agent} from '../agent';
import {AgentService} from '../../../service/agent.service';

@Component({
  selector: 'app-read-agent',
  templateUrl: './read-agent.component.html',
  styleUrls: ['./read-agent.component.css']
})
export class ReadAgentComponent implements OnInit {

  agentId: string;
  agent: Agent;

  constructor(private route: ActivatedRoute, private router: Router, private agentService: AgentService) {
  }

  ngOnInit(): void {
    this.agent = new Agent();

    this.agentId = this.route.snapshot.params['agentId'];


    this.agentService.getAgent(this.agentId).subscribe(data => {
        console.log(data);
        this.agent = data;
      },
      error => console.log(error));
  }

  backToAgents() {
    this.router.navigate(['agents']);
  }

  agentUpdate(agent: Agent) {
    this.router.navigate(['updateAgent', agent]);
  }

  backToManagers() {
    this.router.navigate(['managers']);
  }

}
