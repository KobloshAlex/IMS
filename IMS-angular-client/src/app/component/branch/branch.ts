import {Ceo} from '../ceo/Ceo';
import {Customer} from '../customer/customer';
import {Manager} from '../manager/manager';

export class Branch {
  branchId: string;
  branchName: string;
  branchAddress: string;
  branchCity: string;
  branchState: string;
  phone: string;
  ceo: Ceo;
  customers: Customer[];
  managers: Manager[];
}
