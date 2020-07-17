import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  private baseUrl = 'http://localhost:8080/api/agents';

  constructor(private http: HttpClient) {
  }

  getAgent(agentId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + agentId);
  }

  createAgent(agent: Object): Observable<Object> {
    return this.http.post(this.baseUrl, agent);
  }

  updateAgent(agent: Object, id: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + id, agent);
  }

  deleteAgent(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAllAgents(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  addCustomerPolicyToAgent(agent: Object, agentId: string, customerPolicyId: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + agentId + '/add-customer-policy/' + customerPolicyId, agent);
  }
}
