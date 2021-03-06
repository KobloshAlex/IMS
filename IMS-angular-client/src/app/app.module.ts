import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {CreateCustomerComponent} from './component/customer/create-customer/create-customer.component';
import {ReadCustomerComponent} from './component/customer/read-customer/read-customer.component';
import {UpdateCustomerComponent} from './component/customer/update-customer/update-customer.component';
import {ShowCustomersComponent} from './component/customer/show-customers/show-customers.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {_MatMenuDirectivesModule, MatMenuModule} from '@angular/material/menu';
import {MatPaginatorModule} from '@angular/material/paginator';
import {CreateAgentComponent} from './component/agent/create-agent/create-agent.component';
import {ReadAgentComponent} from './component/agent/read-agent/read-agent.component';
import {ShowAgentsComponent} from './component/agent/show-agents/show-agents.component';
import {UpdateAgentComponent} from './component/agent/update-agent/update-agent.component';
import {CreateManagerComponent} from './component/manager/create-manager/create-manager.component';
import {ReadManagerComponent} from './component/manager/read-manager/read-manager.component';
import {ShowManagerComponent} from './component/manager/show-manager/show-manager.component';
import {UpdateManagerComponent} from './component/manager/update-manager/update-manager.component';
import {CreateCeoComponent} from './component/ceo/create-ceo/create-ceo.component';
import {ReadCeoComponent} from './component/ceo/read-ceo/read-ceo.component';
import {ShowCeoComponent} from './component/ceo/show-ceo/show-ceo.component';
import {UpdateCeoComponent} from './component/ceo/update-ceo/update-ceo.component';
import {CreateBranchComponent} from './component/branch/create-branch/create-branch.component';
import {ReadBranchComponent} from './component/branch/read-branch/read-branch.component';
import {ShowBranchComponent} from './component/branch/show-branch/show-branch.component';
import {UpdateBranchComponent} from './component/branch/update-branch/update-branch.component';
import {CreatePolicyComponent} from './component/policy/create-policy/create-policy.component';
import {ReadPolicyComponent} from './component/policy/read-policy/read-policy.component';
import {ShowPolicyComponent} from './component/policy/show-policy/show-policy.component';
import {UpdatePolicyComponent} from './component/policy/update-policy/update-policy.component';
import {CreateCustomerPolicyComponent} from './component/customer-policy/create-customer-policy/create-customer-policy.component';
import {ReadCustomerPolicyComponent} from './component/customer-policy/read-customer-policy/read-customer-policy.component';
import {ShowCustomerPolicyComponent} from './component/customer-policy/show-customer-policy/show-customer-policy.component';
import {UpdateCustomerPolicyComponent} from './component/customer-policy/update-customer-policy/update-customer-policy.component';
import {AddBranchComponent} from './component/ceo/add-branch/add-branch.component';
import {AddCustomerComponent} from './component/branch/add-customer/add-customer.component';
import {AddManagerComponent} from './component/branch/add-manager/add-manager.component';
import {AddAgentComponent} from './component/manager/add-agent/add-agent.component';
import {AddCustomerPolicyManagerComponent} from './component/manager/add-customer-policy-manager/add-customer-policy-manager.component';
import {AddCustomerPolicyAgentComponent} from './component/agent/add-customer-policy-agent/add-customer-policy-agent.component';
import {AddCustomerPolicyCustomerComponent} from './component/customer/add-customer-policy-customer/add-customer-policy-customer.component';
import {AddCustomerPolicyPolicyComponent} from './component/policy/add-customer-policy-policy/add-customer-policy-policy.component';
import {LoginComponent} from './component/auth/login/login.component';
import {RegisterComponent} from './component/auth/register/register.component';
import {HomeComponent} from './component/template-pages/home/home.component';
import {ProfileComponent} from './component/auth/profile/profile.component';

import {authInterceptorProviders} from './helpers/auth.interceptor';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {WeatherApiComponent} from './component/weather-api/weather-api.component';
import {AboutComponent} from './component/template-pages/about/about.component';
import {ResourcesComponent} from './component/template-pages/resources/resources.component';
import {NgxStripeModule} from 'ngx-stripe';
import {ModalComponent} from './component/payment/modal/modal.component';
import {PaymentComponent} from './component/payment/payment/payment.component';
import {ArticleDetailsComponent} from './component/payment/article/article-details.component';
import {ToastrModule} from 'ngx-toastr';
import { SingleReadComponent } from './component/customer-policy/single-read/single-read.component';
import { FooterComponent } from './component/template-pages/footer/footer.component';

const stripe_public_key = 'pk_test_51H5WtTLS6ZAlEryRY52RAlok50DKKElgTOcidgyzyHLN9GUYTgTIUW33JRQwk1mlnGbUIegpQyPoYYI8H6JC4k1r00J7p8MpGM';

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
    UpdateCeoComponent,
    CreateBranchComponent,
    ReadBranchComponent,
    ShowBranchComponent,
    UpdateBranchComponent,
    CreatePolicyComponent,
    ReadPolicyComponent,
    ShowPolicyComponent,
    UpdatePolicyComponent,
    CreateCustomerPolicyComponent,
    ReadCustomerPolicyComponent,
    ShowCustomerPolicyComponent,
    UpdateCustomerPolicyComponent,
    AddBranchComponent,
    AddCustomerComponent,
    AddManagerComponent,
    AddAgentComponent,
    AddCustomerPolicyManagerComponent,
    AddCustomerPolicyAgentComponent,
    AddCustomerPolicyCustomerComponent,
    AddCustomerPolicyPolicyComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    WeatherApiComponent,
    AboutComponent,
    ResourcesComponent,
    ModalComponent,
    PaymentComponent,
    ArticleDetailsComponent,
    SingleReadComponent,
    FooterComponent,
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
    MatPaginatorModule,
    MatCardModule,
    MatIconModule,
    ReactiveFormsModule,
    NgxStripeModule.forRoot(stripe_public_key),
    ToastrModule.forRoot()
  ],
  exports: [MatButtonModule],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
