# Discovery service

# Profile

- `local`: Run this service on your own local machine.
- `prod`: Run for production. Not set on this project because this is just example.

# Environment Variables

## Necessary

- `DISCOVERY_SERVER_HOSTNAME`: Set hostname for this service would be run.
- `DISCOVERY_SERVER_PORT`: Set port for this service would be run.
- `DISCOVERY_SERVER_PEERING_URLS`: Comma separated urls for each discovery server.
For example `http://{hostname}:{port},http://{hostname}:{port},http://{hostname}:{port}`

## Optional

- `DISCOVERY_SERVER_NODE_ID`: Node id of peered discovery service node. If not set random uuid value will be allocated.

# Notice

- `hostname` must be differed each peering nodes.
- Set host properties on your local machine.

> ### Open host file
>
> ```shell
> # mac
> $ vi /etc/hosts
> 
> # windows
> > notepad C:\Windows\System32\drivers\etc\hosts
> 
> # linux
> % vi /etc/hosts
> ```
>
> ### Append host info
>
> - I appended some host info like below
>
> ```shell
> # Custom hosts - yyyy MMM dd appended
> 127.0.0.1       discovery00
> 127.0.0.1       discovery01
> 127.0.0.1       discovery02
> ```
