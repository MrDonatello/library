import React from "react";
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import AppNavBar from "../AppNavBar";

class Author extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            authors: []
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/library/author').then(response => response.json()).then(data => this.setState({authors: data}));

    };

    async remove(id) {
        await fetch(`/library/author/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedAuthors = [...this.state.authors].filter(i => i.id !== id);
            this.setState({authors: updatedAuthors});
        });
    }

    render() {
        const {authors, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const author = authors.map(author => {
            return <tr key={author.id}>
                <td style={{whiteSpace: 'nowrap'}}>{author.firstName}</td>
                <td>{author.lastName}</td>
                <td>{author.patronymic}</td>
                <td>{author.biography}</td>
                <td>{author.yearOfBirth}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/library/author/" + author.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(author.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/library/author/new">Add Author</Button>
                    </div>
                    <h3>Author</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>patronymic</th>
                            <th>biography</th>
                            <th>yearOfBirth</th>
                        </tr>
                        </thead>
                        <tbody>
                        {author}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Author;