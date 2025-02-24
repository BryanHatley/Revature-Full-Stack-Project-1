import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import "bootstrap/dist/css/bootstrap.css"
import Login from './Components/LoginRegister/Login'
import Register from './Components/LoginRegister/Register'
import UserTable from './Components/User/UserTable'
import ReimbursementTable from './Components/Reimbursement/ReimbursementTable'
import CreateNewReimbursement from './Components/Reimbursement/CreateNewReimbursement'

function App() {

  return (
    <body className="bg-secondary">
      <BrowserRouter>
        <Routes>
          <Route path="" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/users" element={<UserTable/>}/>
          <Route path="/reimbursements" element={<ReimbursementTable/>}/>
          <Route path="/reimbursements/new" element={<CreateNewReimbursement/>}/>
        </Routes>
      </BrowserRouter>
    </body>
  )
}

export default App
