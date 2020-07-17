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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {_MatMenuDirectivesModule, MatMenuModule} from '@angular/material/menu';
import {MatPaginatorModule} from '@angular/material/paginator';
import { CreateAgentComponent } from './component/agent/create-agent/create-agent.component';
import { ReadAgentComponent } from './component/agent/read-agent/read-agent.component';
import { ShowAgentsComponent } from './component/agent/show-agents/show-agents.component';
import { UpdateAgentComponent } from './component/agent/update-agent/update-agent.component';
import { CreateManagerComponent } from './component/manager/create-manager/create-manager.component';
import { ReadManagerComponent } from './component/manager/read-manager/read-manager.component';
import { ShowManagerComponent } from './component/manager/show-manager/show-manager.component';
import { UpdateManagerComponent } from './component/manager/update-manager/update-manager.component';
import { CreateCeoComponent } from './component/ceo/create-ceo/create-ceo.component';
import { ReadCeoComponent } from './component/ceo/read-ceo/read-ceo.component';
import { ShowCeoComponent } from './component/ceo/show-ceo/show-ceo.component';
import { UpdateCeoComponent } from './component/ceo/update-ceo/update-ceo.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateCustomerComponent,
    ReadCustomerComponent,
    UpdateCustomerComponent,
    ShowCustomersComponent,
    CreateAgentComponent,
    ReadAgentComponent,
    ShowAgentsComponent,
    UpdateAgentComponent,
    CreateManagerComponent,
    ReadManagerComponent,
    ShowManagerComponent,
    UpdateManagerComponent,
    CreateCeoComponent,
    ReadCeoComponent,
    ShowCeoComponent,
    UpdateCeoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule,
    _MatMenuDirectivesModule,
    MatMenuModule,
    MatPaginatorModule
  ],
  exports: [MatButtonModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
