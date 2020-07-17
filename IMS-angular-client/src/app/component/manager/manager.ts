import {Branch} from '../branch/branch';
import {Agent} from '../agent/agent';
import {CustomerPolicy} from '../customer-policy/customerPolicy';

export class Manager {
  branchManagerId: string;
  firstName: string;
  lastName: string;
  age: number;
  sex: string;
  branchAddress: string;
  branchCity: string;
  branchState: string;
  password: string;
  email: string;
  branch: Branch;
  agents: Agent[];
  customerPolicies: CustomerPolicy[];
}
