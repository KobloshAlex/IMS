import {Manager} from '../manager/manager';

export class CustomerPolicy {
  customerPolicyId: string;
  premiumType: string;
  nomineeName: string;
  relational: string;
  agentCommissionAmount: number;
  termConditions: boolean;
  manager: Manager;
}

