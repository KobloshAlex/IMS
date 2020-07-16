import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Agent} from '../agent';
import {AgentService} from '../../../service/agent.service';

@Component({
  selector: 'app-update-agent',
  templateUrl: './update-agent.component.html',
  styleUrls: ['./update-agent.component.css']
})
export class UpdateAgentComponent implements OnInit {

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

  updateAgent() {
    this.agentService.updateAgent(this.agent, this.agentId).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.agent = new Agent();
    this.gotoList();
  }

  onSubmit() {
    this.updateAgent();
  }

  gotoList() {
    this.router.navigate(['/agents']);
  }
}
