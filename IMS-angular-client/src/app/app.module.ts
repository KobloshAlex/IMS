import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {CreateCustomerComponent} from './component/customer/create-customer/create-customer.component';
import {ReadCustomerComponent} from './component/customer/read-customer/read-customer.component';
import {UpdateCustomerComponent} from './component/customer/update-customer/update-customer.component';
import {ShowCustomersComponent} from './component/customer/show-customers/show-customers.component';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    CreateCustomerComponent,
    ReadCustomerComponent,
    UpdateCustomerComponent,
    ShowCustomersComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
