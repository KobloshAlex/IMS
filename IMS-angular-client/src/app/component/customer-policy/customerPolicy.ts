import {Manager} from '../manager/manager';
import {Agent} from '../agent/agent';
import {Customer} from '../customer/customer';

export class CustomerPolicy {
  customerPolicyId: string;
  premiumType: string;
  nomineeName: string;
  relational: string;
  agentCommissionAmount: number;
  termConditions: boolean;
  manager: Manager;
  agent: Agent;
  customer: Customer;
}

