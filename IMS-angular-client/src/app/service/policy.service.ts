import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PolicyService {

  private baseUrl = 'http://localhost:8080/cogent-insurance/api/policy';

  constructor(private http: HttpClient) {
  }

  getPolicy(policyId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + policyId);
  }

  createPolicy(policy: Object): Observable<Object> {
    return this.http.post(this.baseUrl, policy);
  }

  updatePolicy(policy: Object, id: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + id, policy);
  }

  deletePolicy(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAllPolicies(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  addCustomerPolicyToPolicy(policy: Object, policyId: string, customerPolicyId: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + policyId + '/add-customer-policy/' + customerPolicyId, policy);
  }
}
