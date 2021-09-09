import React from "react";
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import AppNavBar from "../AppNavBar";

class User extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            users: []
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/library/user').then(response => response.json()).then(data => this.setState({users: data}));

    };

    async remove(id) {
        await fetch(`/library/user/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedUsers = [...this.state.users].filter(i => i.id !== id);
            this.setState({users: updatedUsers});
        });
    }

    render() {
        const {users, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const user = users.map(user => {
            return <tr key={user.id}>
                <td style={{whiteSpace: 'nowrap'}}>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.patronymic}</td>
                <td>{user.yearOfBirth}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/library/user/" + user.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(user.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/library/user/new">Add User</Button>
                    </div>
                    <h3>User</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>patronymic</th>
                            <th>yearOfBirth</th>
                        </tr>
                        </thead>
                        <tbody>
                        {user}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default User;