import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Agent} from '../agent';
import {AgentService} from '../../../service/agent.service';
import {Ceo} from '../../ceo/Ceo';

@Component({
  selector: 'app-create-agent',
  templateUrl: './create-agent.component.html',
  styleUrls: ['./create-agent.component.css']
})
export class CreateAgentComponent implements OnInit {
  isError = false;
  isSuccessful = false;
  errorMessage = '';
  agent: Agent = new Agent();

  constructor(private route: ActivatedRoute, private router: Router, private agentService: AgentService) {
  }

  ngOnInit(): void {
  }

  saveAgent() {
    this.agentService.createAgent(this.agent).subscribe(data => {
        this.isSuccessful = true
        this.isError = false;
        console.log(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.agent = new Agent();
  }

  onSubmit() {
    this.saveAgent();
  }

  gotoList() {
    this.router.navigate(['/agents']);
  }
}
