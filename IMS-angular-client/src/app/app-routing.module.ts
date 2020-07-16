import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ShowCustomersComponent} from './component/customer/show-customers/show-customers.component';
import {CreateCustomerComponent} from './component/customer/create-customer/create-customer.component';
import {UpdateCustomerComponent} from './component/customer/update-customer/update-customer.component';
import {ReadCustomerComponent} from './component/customer/read-customer/read-customer.component';

const routes: Routes = [
  {path: 'customers', component: ShowCustomersComponent},
  {path: 'add', component: CreateCustomerComponent},
  {path: 'update', component: UpdateCustomerComponent},
  {path: 'details/:customerId', component: ReadCustomerComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
