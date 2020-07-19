import {CustomerPolicy} from '../customer-policy/customerPolicy';

export class Policy {
  policyId: string;
  policyName: string;
  policyTerm: number;
  policyAmount: number;
  policyInterest: number;
  maturityAmount: number;
  policyType: string;
  date: string;
  customerPolicies: CustomerPolicy[];
}
