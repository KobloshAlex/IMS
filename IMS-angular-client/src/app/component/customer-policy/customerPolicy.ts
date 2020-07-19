import {Manager} from '../manager/manager';
import {Agent} from '../agent/agent';
import {Customer} from '../customer/customer';
import {Policy} from '../policy/policy';

export class CustomerPolicy {
  customerPolicyId: string;
  premiumType: string;
  nomineeName: string;
  relational: string;
  agentCommissionAmount: number;
  termConditions: boolean;
  branchManager: Manager;
  agentEntity: Agent;
  customerEntity: Customer;
  policyEntity: Policy;
}

