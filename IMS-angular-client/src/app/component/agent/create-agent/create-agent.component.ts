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
  errorMessage = '';
  agent: Agent = new Agent();

  constructor(private route: ActivatedRoute, private router: Router, private agentService: AgentService) {
  }

  ngOnInit(): void {
  }

  saveAgent() {
    this.agentService.createAgent(this.agent).subscribe(data => {
        console.log(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isError = true;
      }
    );
    this.agent = new Agent();
    if (this.isError == true) {
      this.gotoList();
    }
  }

  onSubmit() {
    this.saveAgent();
  }

  gotoList() {
    this.router.navigate(['/agents']);
  }
}
