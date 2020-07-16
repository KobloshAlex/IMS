import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ShowCustomersComponent} from './component/customer/show-customers/show-customers.component';
import {CreateCustomerComponent} from './component/customer/create-customer/create-customer.component';
import {UpdateCustomerComponent} from './component/customer/update-customer/update-customer.component';
import {ReadCustomerComponent} from './component/customer/read-customer/read-customer.component';
import {ShowAgentsComponent} from './component/agent/show-agents/show-agents.component';
import {CreateAgentComponent} from './component/agent/create-agent/create-agent.component';
import {UpdateAgentComponent} from './component/agent/update-agent/update-agent.component';
import {ReadAgentComponent} from './component/agent/read-agent/read-agent.component';

const routes: Routes = [
  {path: 'customers', component: ShowCustomersComponent},
  {path: 'add', component: CreateCustomerComponent},
  {path: 'update', component: UpdateCustomerComponent},
  {path: 'details/:customerId', component: ReadCustomerComponent},

  {path: 'agents', component: ShowAgentsComponent},
  {path: 'addAgent', component: CreateAgentComponent},
  {path: 'updateAgent', component: UpdateAgentComponent},
  {path: 'agentDetails/:agentId', component: ReadAgentComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
