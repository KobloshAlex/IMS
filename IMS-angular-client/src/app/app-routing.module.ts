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
import {ShowBranchComponent} from './component/branch/show-branch/show-branch.component';
import {CreateBranchComponent} from './component/branch/create-branch/create-branch.component';
import {UpdateBranchComponent} from './component/branch/update-branch/update-branch.component';
import {ReadBranchComponent} from './component/branch/read-branch/read-branch.component';
import {ShowPolicyComponent} from './component/policy/show-policy/show-policy.component';
import {CreatePolicyComponent} from './component/policy/create-policy/create-policy.component';
import {UpdatePolicyComponent} from './component/policy/update-policy/update-policy.component';
import {ReadPolicyComponent} from './component/policy/read-policy/read-policy.component';
import {ShowCustomerPolicyComponent} from './component/customer-policy/show-customer-policy/show-customer-policy.component';
import {CreateCustomerPolicyComponent} from './component/customer-policy/create-customer-policy/create-customer-policy.component';
import {UpdateCustomerPolicyComponent} from './component/customer-policy/update-customer-policy/update-customer-policy.component';
import {ReadCustomerPolicyComponent} from './component/customer-policy/read-customer-policy/read-customer-policy.component';
import {AddBranchComponent} from './component/ceo/add-branch/add-branch.component';
import {AddCustomerComponent} from './component/branch/add-customer/add-customer.component';
import {AddManagerComponent} from './component/branch/add-manager/add-manager.component';
import {AddAgentComponent} from './component/manager/add-agent/add-agent.component';
import {AddCustomerPolicyManagerComponent} from './component/manager/add-customer-policy-manager/add-customer-policy-manager.component';
import {AddCustomerPolicyAgentComponent} from './component/agent/add-customer-policy-agent/add-customer-policy-agent.component';
import {AddCustomerPolicyCustomerComponent} from './component/customer/add-customer-policy-customer/add-customer-policy-customer.component';
import {AddCustomerPolicyPolicyComponent} from './component/policy/add-customer-policy-policy/add-customer-policy-policy.component';
import {ProfileComponent} from './component/auth/profile/profile.component';
import {RegisterComponent} from './component/auth/register/register.component';
import {LoginComponent} from './component/auth/login/login.component';
import {HomeComponent} from './component/home/home.component';

const routes: Routes = [
  {path: 'customers', component: ShowCustomersComponent},
  {path: 'add', component: CreateCustomerComponent},
  {path: 'update', component: UpdateCustomerComponent},
  {path: 'details/:customerId', component: ReadCustomerComponent},
  {path: 'customers/addCustomerPolicy', component: AddCustomerPolicyCustomerComponent},

  {path: 'agents', component: ShowAgentsComponent},
  {path: 'addAgent', component: CreateAgentComponent},
  {path: 'updateAgent', component: UpdateAgentComponent},
  {path: 'agentDetails/:agentId', component: ReadAgentComponent},
  {path: 'agents/addCustomerPolicy', component: AddCustomerPolicyAgentComponent},

  {path: 'managers', component: ShowManagerComponent},
  {path: 'addManager', component: CreateManagerComponent},
  {path: 'updateManager', component: UpdateManagerComponent},
  {path: 'managerDetails/:branchManagerId', component: ReadManagerComponent},
  {path: 'managers/addAgent', component: AddAgentComponent},
  {path: 'managers/addCustomerPolicy', component: AddCustomerPolicyManagerComponent},

  {path: 'ceos', component: ShowCeoComponent},
  {path: 'addCeo', component: CreateCeoComponent},
  {path: 'updateCeo', component: UpdateCeoComponent},
  {path: 'ceoDetails/:ceoId', component: ReadCeoComponent},
  {path: 'ceo/addBranch', component: AddBranchComponent},

  {path: 'branches', component: ShowBranchComponent},
  {path: 'addBranch', component: CreateBranchComponent},
  {path: 'updateBranch', component: UpdateBranchComponent},
  {path: 'branchDetails/:branchId', component: ReadBranchComponent},
  {path: 'branches/addCustomer', component: AddCustomerComponent},
  {path: 'branches/addManager', component: AddManagerComponent},

  {path: 'policies', component: ShowPolicyComponent},
  {path: 'addPolicy', component: CreatePolicyComponent},
  {path: 'updatePolicy', component: UpdatePolicyComponent},
  {path: 'policyDetails/:policyId', component: ReadPolicyComponent},
  {path: 'policies/addCustomerPolicy', component: AddCustomerPolicyPolicyComponent},

  {path: 'customerPolicies', component: ShowCustomerPolicyComponent},
  {path: 'addCustomerPolicy', component: CreateCustomerPolicyComponent},
  {path: 'updateCustomerPolicy', component: UpdateCustomerPolicyComponent},
  {path: 'customerPolicyDetails/:customerPolicyId', component: ReadCustomerPolicyComponent},

  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
