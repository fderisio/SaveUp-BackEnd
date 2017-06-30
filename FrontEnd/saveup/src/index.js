import React from 'react';
import ReactDOM from 'react-dom';
import Welcome from './Containers/Welcome';
import Dashboard from './Containers/Dashboard';
import SignUp from './Containers/SignUp';
import SignIn from './Containers/SignIn';
import UnderConstruction from './Containers/UnderConstruction';
import Store from './Store';
import registerServiceWorker from './registerServiceWorker';
import './index.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import injectTapEventPlugin from 'react-tap-event-plugin';
import { Provider } from 'react-redux';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

injectTapEventPlugin();

ReactDOM.render(
  <MuiThemeProvider>
    <Provider store={Store} >
      <Router>
      	<Switch>
            <Route exact path="/" component={Welcome} />
            <Route exact path="/signin" component={SignIn} />
            <Route exact path="/signup" component={SignUp} />
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/underconstruction" component={UnderConstruction} />
		    </Switch>
      </Router>
    </Provider>
  </MuiThemeProvider>,
  document.getElementById('root'));

registerServiceWorker();
