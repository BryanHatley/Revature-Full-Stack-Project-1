import { useEffect, useState } from "react";
import { Button, Container, Table } from "react-bootstrap";
import { User } from "../Interfaces/User";
import axios from "axios";
import { store } from "../GlobalData/store";
import { useNavigate } from "react-router-dom";
import Logout from "../LoginRegister/Logout";

function UserTable () 
{
    const navigate = useNavigate();
    const[users, setUsers] = useState<User[]>([]);

    useEffect(() =>
    {
        getAllUsers();
    }, []);

    const getAllUsers = async () =>
    {
        if (store.loggedInUser.role === "manager")
        {
            try
            {
                const response = await axios.get("http://localhost:8080/users", {withCredentials:true});

                setUsers(response.data);
            }
            catch
            {
                alert("Something went wrong trying to fetch users");
            }
        }
        else
        {
            alert("User must be a manger to access this page!")
            navigate("/");
        }
    }

    const deleteUser = async (user:User) =>
    {
        try
        {
            const response = await axios.delete("http://localhost:8080/users/" + user.userId, {withCredentials:true});

            if (response.status.valueOf() == 200)
            {
                alert("User successfully Fired!");
                getAllUsers();
            }
            else
            {
                alert("User deletion failed:\n" + response.data);
            }
        }
        catch
        {
            alert("Something went wrong trying to delete user");
        }
    }

    return(
        <Container className="d-flex flex-column align-items-center mt-3">
            <div>
                <Button className="btn-dark" onClick={() => {Logout(); navigate("/");}}>Logout</Button>
                <Button className="btn-dark" onClick={() => navigate("/reimbursements")}>Reimbursements</Button>
            </div>
            
            <h3>Users:</h3>

            <Table className="table-dark table-hover table-striped w-50">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map((user:User) =>
                        (
                            <tr key={user.userId}>
                                <td>{user.userId}</td>
                                <td>{user.username}</td>
                                <td>{user.role}</td>
                                <td>
                                    {/* <Button variant="outline-success" onClick={() => updateUser(user)}>Promote</Button> */}
                                    <Button variant="outline-danger" onClick={() => deleteUser(user)}>Fire</Button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </Table>
        </Container>
    )
}

export default UserTable;