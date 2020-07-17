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
import {ShowManagerComponent} from './component/manager/show-manager/show-manager.component';
import {CreateManagerComponent} from './component/manager/create-manager/create-manager.component';
import {UpdateManagerComponent} from './component/manager/update-manager/update-manager.component';
import {ReadManagerComponent} from './component/manager/read-manager/read-manager.component';
import {ShowCeoComponent} from './component/ceo/show-ceo/show-ceo.component';
import {CreateCeoComponent} from './component/ceo/create-ceo/create-ceo.component';
import {UpdateCeoComponent} from './component/ceo/update-ceo/update-ceo.component';
import {ReadCeoComponent} from './component/ceo/read-ceo/read-ceo.component';

const routes: Routes = [
  {path: 'customers', component: ShowCustomersComponent},
  {path: 'add', component: CreateCustomerComponent},
  {path: 'update', component: UpdateCustomerComponent},
  {path: 'details/:customerId', component: ReadCustomerComponent},

  {path: 'agents', component: ShowAgentsComponent},
  {path: 'addAgent', component: CreateAgentComponent},
  {path: 'updateAgent', component: UpdateAgentComponent},
  {path: 'agentDetails/:agentId', component: ReadAgentComponent},

  {path: 'managers', component: ShowManagerComponent},
  {path: 'addManager', component: CreateManagerComponent},
  {path: 'updateManager', component: UpdateManagerComponent},
  {path: 'managerDetails/:branchManagerId', component: ReadManagerComponent},

  {path: 'ceos', component: ShowCeoComponent},
  {path: 'addCeo', component: CreateCeoComponent},
  {path: 'updateCeo', component: UpdateCeoComponent},
  {path: 'ceoDetails/:ceoId', component: ReadCeoComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
