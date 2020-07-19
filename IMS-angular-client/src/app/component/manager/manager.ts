import {Branch} from '../branch/branch';
import {Agent} from '../agent/agent';
import {CustomerPolicy} from '../customer-policy/customerPolicy';

export class Manager {
  branchManagerId: string;
  managerId: string; //todo: managerId naming problem(change name in back-end entity)
  firstName: string;
  lastName: string;
  age: number;
  sex: string;
  branchAddress: string;
  branchCity: string;
  branchState: string;
  password: string;
  email: string;
  branchEntity: Branch;
  agents: Agent[];
  customerPolicies: CustomerPolicy[];
}
