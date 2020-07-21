import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerPolicyService {

  private baseUrl = 'http://localhost:8080/cogent-insurance/api/customer-policies';

  constructor(private http: HttpClient) {
  }

  getCustomerPolicy(customerPolicyId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + customerPolicyId);
  }

  createCustomerPolicy(customerPolicy: Object): Observable<Object> {
    return this.http.post(this.baseUrl, customerPolicy);
  }

  updateCustomerPolicy(customerPolicy: Object, id: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + id, customerPolicy);
  }

  deleteCustomerPolicy(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAllCustomerPolicies(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
}
