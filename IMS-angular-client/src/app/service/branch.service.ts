import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  private baseUrl = 'http://localhost:8080/api/branch';

  constructor(private http: HttpClient) {
  }

  getBranch(branchId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/' + branchId);
  }

  createBranch(branch: Object): Observable<Object> {
    return this.http.post(this.baseUrl, branch);
  }

  updateBranch(branch: Object, id: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + id, branch);
  }

  deleteBranch(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + '/' + id);
  }

  getAllBranchs(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  addCustomerToBranch(branch: Object, branchId: string, customerId: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + branchId + '/add-customer/' + customerId, branch);
  }

  addManagerToBranch(branch: Object, branchId: string, managerId: string): Observable<Object> {
    return this.http.put(this.baseUrl + '/' + branchId + '/add-manager/' + managerId, branch);
  }
}
