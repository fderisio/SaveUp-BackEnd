import React from 'react';
import FlatButton from 'material-ui/FlatButton';
import '../../style.css';
import Navbar from '../Navbar';
import ExpensesTable from '../../Components/ExpensesTable';
import MonthFolders from '../../Components/MonthFolders';
import Footer from '../../Components/Footer';

class Dashboard extends React.Component {

  render() {
    return (
      <div>
        <Navbar />
        <div className="wrapper">
          <div className="MonthFolders">
            <MonthFolders />
          </div>
          <div className="ExpensesTable">
            <p>Recent Expenses</p>
            <ExpensesTable />
          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return state;
}

export default Dashboard;
