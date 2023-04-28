import {BrowserRouter as Router,Routes,Route} from 'react-router-dom'
import LoginPage from "./pages/LoginPage";
import MyTodoPage from "./pages/MyTodoPage";
import RegisterPage from "./pages/RegisterPage";

function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={ <MyTodoPage/>} />
              <Route path="/Login" element={ <LoginPage/>} />
              <Route path="/Register" element={ <RegisterPage/>} />
          </Routes>
      </Router>
  );
}

export default App;
