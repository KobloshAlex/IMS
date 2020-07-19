import {Manager} from '../manager/manager';
import {CustomerPolicy} from '../customer-policy/customerPolicy';

export class Agent {
  agentId: string;
  firstName: string;
  lastName: string;
  age: number;
  sex: string;
  branchAddress: string;
  branchCity: string;
  branchState: string;
  password: string;
  email: string;
  branchManager: Manager;
  customerPolicies: CustomerPolicy[];
}
