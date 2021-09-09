import React from "react";
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import AppNavBar from "../AppNavBar";

class Genre extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            genres: []
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/library/genre').then(response => response.json()).then(data => this.setState({genres: data}));

    };

    async remove(id) {
        await fetch(`/library/genre/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedGenre = [...this.state.genres].filter(i => i.id !== id);
            this.setState({genres: updatedGenre});
        });
    }

    render() {
        const {genres, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const genre = genres.map(genre => {
            return <tr key={genre.id}>
                <td style={{whiteSpace: 'nowrap'}}>{genre.title}</td>
                <td>{genre.description}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/library/genre/" + genre.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(genre.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/library/genre/new">Add Genre</Button>
                    </div>
                    <h3>Genre</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        {genre}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Genre;