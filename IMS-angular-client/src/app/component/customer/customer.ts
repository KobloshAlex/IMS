import {Branch} from '../branch/branch';
import {CustomerPolicy} from '../customer-policy/customerPolicy';

export class Customer {
  customerId: string;
  firstName: string;
  lastName: string;
  age: number;
  sex: symbol;
  password: string;
  address: string;
  email: string;
  branchEntity: Branch;
  customerPolicies: CustomerPolicy[];
}
