import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  private baseUrl = 'http://localhost:8080/cogent-insurance/api/branch-managers';

  constructor(private http: HttpClient) {
  }

  getManager(managerId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + managerId);
  }

  createManager(manager: Object): Observable<Object> {
    return this.http.post(this.baseUrl, manager);
  }

  updateManager(manager: Object, id: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + id, manager);
  }

  deleteManager(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAllManagers(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  addAgentToManager(manager: Object, managerId: string, agentId: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + managerId + '/add-agent/' + agentId, manager);
  }

  addCustomerPolicyToManager(manager: Object, managerId: string, customerPolicy: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + managerId + '/add-customer-policy/' + customerPolicy, manager);
  }
}
